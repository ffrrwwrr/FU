let links = [
    {
        name: 'first',
        title: 'One',
    },
    {
        name: 'second',
        title: 'Two',
    },
    {
        name: 'хз как будет по английски третий',
        title: 'Three',
    },
    {
        name: 'четвертый',
        title: 'Four',
    },
    {
        name: 'пятый',
        title: 'Five',
    }
];

function onClickButton() {
    clearResult();
    renderTable();
}

function renderGetLinksAmountButton() {
    const button = document.createElement('button');
    button.innerHTML = 'Получить количество ссылок';
    button.setAttribute('onClick', 'onGetLinksAmount()');

    return button;
}

function onGetLinksAmount() {
    const selectValue = document.getElementById('select').value;
    alert(`Кол-во ссылок в таблице: ${selectValue}`);
}

function clearResult() {
    document.getElementById('result').innerHTML = '';
}

function renderTable() {
    const selectValue = document.getElementById('select').value;
    const resultDiv = document.getElementById('result');
    const table = document.createElement('table');
    table.border = '1';
    const tr = document.createElement('tr');

    for (let i = 0; i < selectValue; i++) {
        const td = document.createElement('td');
        const link = createLink(links[i]);
        td.appendChild(link);
        tr.appendChild(td);
    }

    table.appendChild(tr);
    resultDiv.appendChild(table);
    resultDiv.appendChild(renderGetLinksAmountButton());
}

function createLink(link) {
    const createdLink = document.createElement('a');
    createdLink.setAttribute('name', link.name);
    createdLink.setAttribute('href', '#');
    createdLink.innerHTML = link.title;

    return createdLink;
}

function renderOptions(links) {
    const wrapper = document.getElementById('select');

    links.forEach((_link, i) => {
        const option = document.createElement('option');
        const normalIdx = i + 1;
        option.value = normalIdx;
        option.innerHTML = normalIdx;
        wrapper.appendChild(option);
    });
}

function init(links) {
    renderOptions(links);
}

init(links);