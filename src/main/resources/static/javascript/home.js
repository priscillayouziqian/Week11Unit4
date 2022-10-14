//STEP 1: A cookie we need to read to get the logged in user's id
//cookie 
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM elements
const submitForm = document.getElementById("note-form")
const noteContainer = document.getElementById("note-container")

//Modal elements
let noteBody = document.getElementById("note-body")
let updateNoteBtn = document.getElementById("update-note-button")

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl="http://localhost:8080/api/v1/notes/"

//STEP 2: because we have a logged in user that kept track with a cookie, we need a logout method to clear that cookie
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}