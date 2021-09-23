function onclick1 () {
    const text = document.getElementById("textId").value
    alert(`Кол-во ссылок начинающихся на "http:" : ${text.match(/(^http:[\/][\/])([\w][\w][\w][\.]?)([a-zA-Z0-9]*)([\.])([a-z]*$)/gm).length}`)
}