// 1. add a new item to the to-do list

const enterButton = document.querySelector('#enter');

const createNewToDo = () => {
	// assign value of newToDo to new <li>
	const newToDo = document.querySelector('#new-todo');
	const newListItem = document.createElement('li');
	newListItem.innerText = newToDo.value;

	// create delete button, append to li
	const deleteButton = document.createElement('button');
	deleteButton.innerText = 'Delete';
	deleteButton.className = "delete-button";
	deleteButton.addEventListener('click', () => {
		handleDelete(newListItem)
	})
	newListItem.appendChild(deleteButton);

	// add check box, append to li
	const checkbox = document.createElement('input')
	checkbox.type = "checkbox"
	checkbox.addEventListener('change', () => {
		handleCheckbox(newListItem)
	})
	newListItem.appendChild(checkbox)

	// add li to ul
	const list = document.querySelector('#list');
	list.appendChild(newListItem);
}

enterButton.addEventListener('click', () => createNewToDo());

// 2. a working delete button
const handleDelete = (toDo) => {
	const list = document.querySelector('#list');
	list.removeChild(toDo);
}

// 3. show date
const dateButton = document.querySelector('#show-date');
dateButton.addEventListener('click', () => {
	const dateDisplay = document.querySelector('#date-today');
	dateDisplay.innerHTML = Date();
})

// 4. Check off items
const handleCheckbox = (toDo) => {
	// remove from list
	const list = document.querySelector('#list');
	list.removeChild(toDo);
	// add to 'done' list
	const doneList = document.querySelector('#done-items')
	doneList.appendChild(toDo)
}


