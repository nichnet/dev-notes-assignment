// Simple (client-side - haha) text sanitization to stop JS injection (eg. <script>..).
const sanitizeUserText = text => text.replace(/[<>]/g, '');

const addToTable = (data, clearTable) => {
    const listContainer = $('#notesList');

    if (!Array.isArray(data)) {
        data = [data];
    }

    if(clearTable && data.length > 0) {
        listContainer.empty();
    }

    for(const note of data) {
        const date = new Date(note.date_created);
        const formattedDate = date.toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit',
            hour12: true,
        });

        const formattedText = sanitizeUserText(note.value);

        // Add to the top of the list.
        listContainer.prepend(`
            <div class="note border">
                <p>${formattedText}</p>
                <p class="date border">${formattedDate}</p>
            </div>
        `);
    }
};

// On load, get all notes
$.ajax({
    url: '/notes',
    type: 'GET',
    success: (response) => {
        addToTable(response, true);
    },
    error: (err) => {
        console.log("Error fetching notes.");
    }
});

$(document).ready(function() {
    $('#notesForm').on('submit', (event) => {
        event.preventDefault(); // Don't reload page.
        const data = {
            value: $('#value').val(),
        };

        $.ajax({
            url: '/notes',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: (response) => {
                addToTable(response);
                alert("Note created.");
                $('#notesForm')[0].reset();
            },
            error: (err) => {
                alert("Error creating notes.");
            }
        });
    });
});