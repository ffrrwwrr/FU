function onclick1 () {
    const text = document.getElementById("textId").value
    alert(text.match(/(^http:[\/][\/])([\w][\w][\w][\.]?)([a-zA-Z0-9]*)([\.])([a-z]*$)/gm).length)
}