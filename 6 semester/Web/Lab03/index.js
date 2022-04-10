const express = require('express');
const axios = require('axios');
const { send, sendFile } = require('express/lib/response');

const port = '3000';
const app = express();
const server = require('http').createServer(app);

app.get('/', (req, res) => {
  res.sendFile('./src/index.html');
});

app.get('/get/:val', (req, res) => {
  let val = req.params.val;
  // console.log(req.params.val) 
  let response = null;
  new Promise(async (resolve, reject) => {
    try {
      response = await axios('https://www.cbr-xml-daily.ru/daily_json.js');
    } catch (err) {
      response = null;
      // console.log(err);
      // reject(err);
    }
    if (response) {
      // success
      const json = response.data;
      let value = json['Valute'][val]['Value']
      console.log(`${val}: ${value}`)
      res.send(`${val}: ${value}`)
      // console.log(`USD: ${json['Valute']['USD']['Value']}`);
      // resolve(json);
      // res.send(`USD: ${json['Valute']['USD']['Value']}`);
    }
  })
})

server.listen(port, function(){
  console.log(`Listening on port ${port} ... `)
})