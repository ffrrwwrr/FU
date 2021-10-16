let idx = 1;

function sendForm() {
    let cols = ['id', 'surname', 'name', 'patronymic']

    let resTable = document.querySelector('#resTable');
    let tr = document.createElement("tr")
    tr.id = idx;

    for (let i=0; i < cols.length; ++i) {
        let td = document.createElement("td")

        const colVal = document.getElementById(cols[i]);

        if (colVal && colVal.value) {
            td.textContent = document.getElementById(cols[i]).value
        } else {
            td.textContent = idx;
            idx++;
        }

        tr.appendChild(td)
        resTable.appendChild(tr)
    }
}

dunct