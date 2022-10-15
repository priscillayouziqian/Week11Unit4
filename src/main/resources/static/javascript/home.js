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

//STEP 3: a form to submit new notes
const handleSubmit = async (e) => {
    e.preventDefault()

    let bodyObj = {
        body: document.getElementById("note-input").value
    }
    await addNote(bodyObj);
    document.getElementById("note-input").value = ''
}

async function addNote(obj){
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if(response.status == 200){
        return getNotes(userId);
    }
}

//STEP 4: we want to retrieve all notes that associated with the user when page loads, create cards for them, append them to a container to hold them
async function getNotes(userId){
    await fetch(`${baseUrl}user/${userId}`, {
        method: 'GET',
        headers: headers
    })
        .then(response => response.json())
        .then(data => createNoteCards(data))
        .catch(err => console.error(err))
}

//STEP 5: update a note which involve a separate GET request for just one note, so that we can populate/fill in our modal with it
async function getNoteById(noteId){
    await fetch(baseUrl + noteId, {
        method: 'GET',
        headers: headers
    })
        .then(response => response.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleNoteEdit(noteId){
    let bodyObj ={
        id: noteId,
        body: noteBody.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))
    
    return getNotes(userId);
}

//STEP 6: delete a note
async function handleDelete(noteId){
    await fetch(baseUrl + noteId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))
    return getNotes(userId);
}

//Below are helper functions
//Function createNoteCards: it accepts an array of objects, then loops through the array and creates a note card for each item and appends it to container for the notes.
const createNoteCards = (array) =>{
    noteContainer.innerHTML = ''
    array.forEach(obj => {
        let noteCard = document.createElement("div")
        noteCard.classList.add("m-2")
        noteCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getNoteById(${obj.id})" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#note-edit-modal">Edit</button>
                    </div>
                </div>
            </div>
        `
        noteContainer.append(noteCard);
    })
}

//function populateModal: use an object argument to fill in the fields within the modal, assign a custom "data-" tag to "Save" button element
const populateModal = (obj) => {
    noteBody.innerHTML = ''
    noteBody.innerHTML = obj.body
    updateNoteBtn.setAttribute('data-note-id', obj.id)
}

//invoke getNotes method, add 2 event listeners
getNotes(userId);

submitForm.addEventListener("submit", handleSubmit)

updateNoteBtn.addEventListener("click", (e) => {
    let noteId = e.target.getAttribute('data-note-id')
    handleNoteEdit(noteId);
})