const express = require('express')
const app = express()
const port = 3000
var server = require('http').createServer(app)

app.use(express.static('public'))

app.use(express.urlencoded({
    extended: true
}))

app.get('/scoring', function(req, res){
  res.sendFile('C:/Users/user/Desktop/Web/Lab02/public/index.html');
})

app.post('/scoring', (req, res) => {
  console.log(req.body)
  res.send("OK")

  const sex = document.getElementById('gender')

  // res.send(if (sex == "male"){
  //   alert(0)
  // })
})

server.listen(port, function(){
  console.log(`listening on ${port} ... `);
})