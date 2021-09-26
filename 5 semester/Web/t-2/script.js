function onclick1 () {
    const text = document.getElementById("textId").value
<<<<<<< HEAD
    alert(text.match(/http:/g).length)
}

=======
    alert(`Кол-во ссылок начинающихся на "http:" : ${text.match(/(^http:[\/][\/])([\w][\w][\w][\.]?)([a-zA-Z0-9]*)([\.])([a-z]*$)/gm).length}`)
}
>>>>>>> 7276a42aea78f4fe37f2d42f43ec5a4b9d4666ac
