//STEP 1: grab element from html
const registerForm = document.getElementById('register-form');
const registerUsername = document.getElementById('register-username');
const registerPassword = document.getElementById('register-password');

const headers={
    'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8080/api/v1/users'

//STEP 2: Write callback functions
const handleSubmit = async (e) =>{
    //prevent the default behavior of the form-auto refresh
    e.preventDefault()
    
    //grab the value of the inputs
    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    }

    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers:headers
    })
        .catch(err => console.error(err.message))
    
    const responseArr = await response.json()

    if(response.status === 200){
        window.location.replace(responseArr[0])
    }
}

//STEP 3: Add event listener to connect html elements and functions
registerForm.addEventListener("submit", handleSubmit)