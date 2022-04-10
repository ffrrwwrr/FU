async function getRate() {
  let curr = document.getElementById('select').value;
  let url = 'http://localhost:3000/get/' + curr; //Move name of value to server
  console.log(curr)
  let response = await fetch(url);
  let data = await response.json();
  let course = data['value']; 
  document.getElementById('currency-value').value = course;
};