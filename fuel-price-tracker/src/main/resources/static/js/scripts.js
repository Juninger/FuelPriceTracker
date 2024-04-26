// search function to filter rows in table
function performSearch(inputId, tableSelector) {
    const inputField = document.getElementById(inputId); // selects the correct input field
    const filter = inputField.value.trim().toUpperCase(); // reads user's search-input
    const table = document.querySelector(tableSelector); // selects the correct table
    const rows = table.getElementsByTagName("tr"); // selects all rows in table

// iterates all table rows and hides rows based on search-input
    for (let i = 1; i < rows.length; i++) {
        const td = rows[i].getElementsByTagName("td")[0]; // index of column to search
        const cellValue = td.textContent; // extracts the data from the cell
        if (cellValue.toUpperCase().indexOf(filter) > -1) { // if cell value matches search-input --> index > -1
            rows[i].style.display = ""; // sets row to visible (css display toggle)
        } else {
            rows[i].style.display = "none"; // hides row (css display toggle)
        }
    }
}

// event listener for gasoline search input
document.getElementById('gasoline-search').addEventListener('input', function() {
    performSearch('gasoline-search', '#gasoline-table');
});

// event listener for diesel search input
document.getElementById('diesel-search').addEventListener('input', function() {
    performSearch('diesel-search', '#diesel-table');
});