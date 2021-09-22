function onclick1 () {
    const text = document.getElementById("textId").value
    alert(text.match(/http:/g).length)
}