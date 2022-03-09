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

  var coeff = 0
  var pol = 0
  if (req.body.gender == 'female'){
      coeff = coeff + 0.4
  }

  var age = ((new Date().getTime() - new Date(req.body.birhdate)) / (24 * 3600 * 365.25 * 1000)) | 0;
  if (age > 20){
      if (age > 30){
          coeff = coeff + 0.3
      } else {
          coeff = coeff + age * 0.01
      }
  }

  if (req.body.profession == 'developer' || req.body.profession == 'teacher' || req.body.profession == 'judged'){
      coeff = coeff + 0.55
  } else if (req.body.profession == 'other'){
      coeff = coeff + 0.19
  }

  if (req.body.sphere == 'public'){
      coeff = coeff + 0.21
  }

  pol = pol + req.body.periodOfLife * 0.042
  if (pol > 0.42){
      coeff = coeff + 0.42
  } else {
      coeff = coeff + pol
  }
  coeff = coeff + req.body.periodOfWork * 0.059

  if (req.body.bankAccount == 'on'){
      coeff = coeff + 0.45
  }
  if (req.body.realEstate == 'on'){
      coeff = coeff + 0.35
  }
  if (req.body.insurancePolicy == 'on'){
      coeff = coeff + 0.19
  }

  if (age < 18){
      res.send("Your age does not qualify!")
  }
  else {
      if (coeff > 1.25) {
          res.send(`You can get a loan. Your coefficient: ${coeff}`)
      } else {
          res.send(`You can not get a loan! Your coefficient: ${coeff}`)
      }
      console.log(`Coefficient: ${coeff}`)
  }
})

server.listen(port, function(){
  console.log(`Listening on ${port} ... `);
})