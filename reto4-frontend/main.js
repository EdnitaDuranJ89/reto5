const select = document.getElementById('selectTable')
const datos = document.getElementsByClassName('datos')
const BACKEND_URL = 'http://140.238.236.250:8080/api/'
const CAMPOS = {
  'Category': ['id', 'name', 'description'],
  'Client': ['id', 'email', 'password', 'name', 'age'],
  'Message': ['id', 'message'],
  'Motorbike': ['id', 'name', 'brand', 'year', 'description'],
  'Reservation': ['id', 'startDate', 'devolutionDate', 'status']
}

select.addEventListener("change", (e)=>{
  e.preventDefault()
  Array.from(datos).forEach(dato => {
    dato.classList.remove('visible')
  })
  toggleVisibility( CAMPOS[e.target.value] )
})


const toggleVisibility = (selectores) => {
  selectores.forEach((selector)=>{
    const nodo = document.getElementById(selector)  
    nodo.classList.toggle('visible')
  })
}

const obtener = () => {
  const seleccion = document.getElementById('selectTable').value
  const output = document.getElementById('output')
  const url = `${BACKEND_URL}/${seleccion}/all`
  fetch(url)
  .then(response => response.json())
  .then(data => {
    data.forEach((register)=>{  
      output.innerHTML = printObject(register)
    })
  })
}

const insertar = () => {
  const seleccion = document.getElementById('selectTable').value
  const url = `${BACKEND_URL}${seleccion}/save`
  let jsonString = '{';
  CAMPOS[seleccion].forEach((campo)=>{
    jsonString += `"${campo}": "${document.getElementById(campo).value}",`
  })
  jsonString = jsonString.substr(0, jsonString.length-1)
  jsonString += '}'

  fetch(url, {
    method: 'POST',
    body: jsonString,
    headers: {
      'Content-type': 'application/json; charset=UTF-8'
    }
  }).then(function (response) {
    if (response.ok) {
      return response.json()
    }
    return Promise.reject(response)
   }).catch(function (error) {
    console.warn('Something went wrong.', error)
  })
}

const actualizar = () => {
  const seleccion = document.getElementById('selectTable').value
  const url = `${BACKEND_URL}${seleccion}/save`
  let jsonString = '{';
  CAMPOS[seleccion].forEach((campo)=>{
    jsonString += `"${campo}": "${document.getElementById(campo).value}",`
  })
  jsonString = jsonString.substr(0, jsonString.length-1)
  jsonString += '}'

  fetch(url, {
    method: 'PUT',
    body: jsonString,
    headers: {
      'Content-type': 'application/json; charset=UTF-8'
    }
  }).then(function (response) {
    if (response.ok) {
      return response.json()
    }
    return Promise.reject(response)
   }).catch(function (error) {
    console.warn('Something went wrong.', error)
  })
}

const eliminar = () => {
  const seleccion = document.getElementById('selectTable').value
  const url = `${BACKEND_URL}${seleccion}/save`
  let jsonString = '{';
  jsonString += `"id": "${document.getElementById('id').value}"`
  jsonString += '}'

  fetch(url, {
    method: 'DELETE',
    body: jsonString,
    headers: {
      'Content-type': 'application/json; charset=UTF-8'
    }
  }).then(function (response) {
    if (response.ok) {
      return response.json()
    }
    return Promise.reject(response)
   }).catch(function (error) {
    console.warn('Something went wrong.', error)
  })
}

const printObject = (o) => {
  let out = '';
  for (var p in o) {
    out += p + ': ' + o[p] + '\n';
  }
  return (out);
}