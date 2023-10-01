# React Forms

## Learning Objectives

* Be able to process user input within a React application via a `form`
* Understand what the term "controlled component" means
* Be able to update a complex data structure housed in application state
* Understand the purpose behind validating user input
* Be able to create a means to validate user input for an application

## Introduction

User input not only ensures that your user is engaged but also paves the way to functional, not only informational, web pages. Think online shops, surveys and games. Each of these examples are common on the web and they all have the shared requirement for **user input**. As we've seen previously, HTML itself includes the `<form>` element alongside `<input>` with its many `type` attributes as well as other means of input such as `<textarea>`. These elements handle their own piece of state, which is then collected together and sent on submission of the form, but within React we tend to override this system somewhat. By creating our own `onSubmit` functionality and by handling the pieces of state explicitly with the `useState` hook, we grant ourselves slightly easier control.

We will need to consider more than simply getting hold of a user's information here though. Any time we gather data from an external source it represents a risk to our system as it may not fit the structure we need it to, it may be inaccurate or may contain harmful content such as an SQL injection attack. These represent not only a risk to the integrity of our system in the latter case but also a risk to its ability to fulfill its purpose if inaccurate data creeps in. While third-party tools can be used to prevent malicious content being uploaded it is up to us to ensure the input is fit for purpose elsewhere in the application. We can do that on the front end though validation, preventing the user from submitting information in the wrong format or omitting a critical field in a form.

## HTML Forms

Let's quickly recap how we build and utilise HTML forms. If we wanted to build a simple register form, it would probably look something like this:

```html
<form>
  <input type="text" id="username" />
  <input type="email" id="email" />
  <input type="password" id="password" />
  <input type="password" id="confirm_password" />
  <input type="submit" id="submit" />
</form>
```

> We have removed some other fields for simplicity's sake. Usually, your form elements could contain `label`, `placeholder` and `name` attributes.

Generally, this form would have some type of `action` associated with it, where it would `POST` to a server. But with `react`, things are much more dynamic or 'Controlled'. This means we can do things like form validation while the user is typing.

## React Forms

Our aim for today is to build a registration form using React Forms. It should contain `form validation`, so a user can only sign up on the following conditions:

1. The `username`, `email` and `password` fields are not empty
1. The `username` does not already exist
1. The `password` matches the `confirm_password`

Once all these conditions are met, we will allow the user to sign up.

So let's get started. Firstly, we need to set up a basic react project. Feel free to follow along starting with the `code_start` directory.

If you take a look in the `src` folder of `code_start`, we have set up a basic React app for you. It contains two subdirectories; `containers` and `components`. Within the `containers` directory, we have a `UserContainer.js`, which contains an array of `users` in its `state`.

Both `users` and the method to `setUsers` are **passed as props** to the `Register.js` component, which is where we will create a form. `users` is also passed to the `ListUsers.js` component, where we display a component for each user (`User.js`). If that makes no sense to you, not to worry! We've created a ✨diagram✨!

![Components diagram](../../../assets/react/forms/components-diagram.jpeg)

We've already set up the `<ListUsers />` & `<Users />` components to display users, but we need to work on the `<Register />` component and build up our form.

Open up `Register.js`, and let's begin making our form.

```js title="components/Register.js"
const Register = ({ users }) => {
  return <></>;
};

export default Register;
```

So far our code looks like this. Nothing new here, just an empty component that returns a Fragment. We're going to start building this form a little differently from how we would in standard ol' HTML. First, we will build up everything we need within our components **state**.

```js title="components/Register.js"
const Register = ({ users }) => {
  const [username, setUsername] = React.useState("");
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [confirmPassword, setConfirmPassword] = React.useState("");

  return <></>;
};

export default Register;
```

We have created a new state variable for each field we will need; a `username`, an `email`, a `password`, and a `confirm_password` field. Each field in our state will map to a form element.

You may notice an error under the word `React.useState("")`. If you do, then you need to import `React`. So add this to the top of your file:

```js title="components/Register.js"
import React from "react";
```

Beneath our new state variables, let's create an empty method called `handleSubmit`. We will build it up later but for now, we will just declare it to avoid errors. And below this, we will start to build up our form in `JSX`.

```js title="components/Register.js"
const Register = ({ users }) => {
  // ...
  // State variables
  // ...

  const handleSubmit = (event) => {};
  return <></>;
};

export default Register;
```

Now we can move on to building our form in `JSX`. We do this the same way as we would in HTML, by adding a `<form>` element with some `<input>` elements inside of it. Let's just start with one, for now, to explain what is happening under the hood. So for now we will only build the `username` element.

```js title="components/Register.js"
// ...
return (
  <>
    <h2>Sign up</h2>

    <form>
      <input
        type="text"
        name="username"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
    </form>
  </>
);
// ...
```

Most of this may look familiar from HTML, but there are mainly two new lines that we should discuss:

```js
value={username}
onChange={(event) => setUsername(event.target.value)}
```

### Controlled Forms

The main difference between this and a regular HTML form is we are using a **controlled form**. This means we handle what the user is typing into the form, which is usually done for us. Giving us **all the control**.

<img src ="https://media.giphy.com/media/YO3icZKE2G8OoGHWC9/giphy.gif" alt="The office Gif" height="300">

If we want to control the form element, we need to tell the element (1) where the value is stored, in this case - in our state, and (2) how to update the value, in this case using the `setUsername` hook.

So the `onChange` field is triggered every time the user types something into the form input, and it gives us an `event`, so we set our username to be the `event.target.value`, which is what the user has typed.

Let's add in our other elements. Beneath the `username` `<input>`, we're going to add another element for our `email`, `password` and `confirmPassword`.

```js title="components/Register.js"
// ...
 <input
    type="text"
    name="email"
    placeholder="Email"
    value={email}
    onChange={(e) => setEmail(e.target.value)}
  />
  <input
    type="password"
    name="password"
    value={password}
    placeholder="Password"
    onChange={(e) => setPassword(e.target.value)}
  />
  <input
    type="password"
    name="confirmPassword"
    value={confirmPassword}
    placeholder="Confirm Password"
    onChange={(e) => setConfirmPassword(e.target.value)}
  />
  //...
```

If you head back to your browser, you should be able to type into each input field as usual. If you open up the **React Dev Tools**, and select the `Register` Component, you should see the state variables in there.

<img src ="../../../../assets/react/forms/react_form_example.gif" alt="React Form Example Gif">

If you don't see information updating in the state in your dev tools, you may need to double-check your code to ensure it's hooked up correctly.

### Validation

Now that we have all of the information stored in our state, we can validate the input data.

Firstly let's add an onSubmit property to our `<form>` element:

```js title="components/Register.js"
// ...
return (
      <form onSubmit={handleSubmit}>
      // ...
);
```

And let's add a `submit` button to the bottom of our `<form>`:

```js title="components/Register.js"
// ...
  <input type="submit" value="Submit" />
  </form>
//...
```

By giving this input the `type` of `submit`, it knows that if the user clicks it, it will submit the above form, which triggers the `handleSubmit` function.

Above our `handleSubmit` method, create a new method called `handleValidation`

```js title="components/Register.js"
// ...
const handleValidation = () => {};
// ...
```

If you remember from the introduction, our validation rules are the following:

1. The `username`, `email` and `password` fields are not empty
2. The `username` does not already exist
3. The `password` matches the `confirm_password`

So let's check if the `username` exists. If you remember, we already pass the existing users to our component via props. So in the `handleValidation` method, we can check it.

```js title="components/Register.js"
// ...
const handleValidation = () => {
  let failedValidation = false;

  // Check username
  if (users.find((user) => user.username === username)) {
    failedValidation = true;
  }

  return failedValidation;
};

const handleSubmit = (e) => {};
// ...
```

You can see here we do two things in our check username if statement:
In the above code, we check the `users` array (passed down from the `UserContainer.js`) if the username already exists. If it exists, the validation will fail and the user should not be allowed to register with this username.

Let's add some validation for the password too, so beneath the check username if statement, add the following:

```js title="components/Register.js"
const handleValidation = () => {
  // ...
  // Check the password
  if (password !== confirmPassword) {
    failedValidation = true;
  }

  return failedValidation;
};
```

Here we check if the `password` does **not** match the `confirm_password`. If they don't match, the user has failed the validation.

Finally, let's check if any of the fields are blank because the user should not be allowed to create a user with no username, email or password.

```js title="components/Register.js"
const handleValidation = () => {
  // ...
  // Check if fields are blank
  if (username === "" || email === "" || password === "") {
    failedValidation = true;
  }
  return failedValidation;
};
```

With these three checks, we have successfully handled validation for our form ✅🤠

### Creating the new user

We're almost there! The next step is to complete the `handleSubmit` method. We have passed the `setUsers` method down from the `UserContainer.js`, so we just need to call it with our newly created user!

Updating the state is a little more complicated when working with arrays or objects than strings or numbers though. When we called `setEmail` earlier a re-render was triggered to show our changes on the screen because React compared the value passed to the function to the value already in the state, saw they were different and made the update. Recognising that difference is crucial; if the value passed to `setEmail` was the same as what was already in the state then the re-render wouldn't happen.

When storing arrays and objects in variables they are handled differently from other values by JavaScript. Instead of the actual values we store a _reference_ to the array or object telling the interpreter where to find the values in the system's memory. If we push something to an array or update a value in an object, for example, the reference stored in the variable doesn't change.

In terms of React, that means that simply updating the array in the state won't have any effect. If we tried that in our application the new user being added would show up in the dev tools, but the change wouldn't be reflected in the DOM.

```js title="components/Register.js"
// ...
const handleSubmit = (e) => {
  e.preventDefault();

  if (!handleValidation()) {
    let updatedUsers = users;
    updatedUsers.push({
      username,
      email,
      password,
      confirmPassword,
    });
    setUsers(updatedUsers);
  }
};
// ...
```

Instead, we need to make a copy of the array, update the copy then pass the copy into `setUsers`. We will use the **spread operator** to create a new array with the same values before updating it.

```js title="components/Register.js"
// ...
const handleSubmit = (e) => {
  e.preventDefault();

  if (!handleValidation()) {
    // copy array
    const updatedUsers = [...users];

    // create a new user object
    updatedUsers.push({
      username,
      email,
      password,
      confirmPassword,
    });

    // update state with a new array
    setUsers(updatedUsers);
  }
};
// ...
```

### User-Friendly Validation

At this point, our application functions perfectly, which is great! But there is one problem. If the user has failed validation, **they have no idea why**.

<img src="https://media.giphy.com/media/rAm0u2k17rM3e/giphy.gif" alt="I have no idea what I'm doing" height="250">

This is a very important aspect of Software Development, we need to inform the user that they have made a mistake, and show them how to fix it, so let's quickly add some error messages to our form.

Firstly, let's make a new state variable below the `confirmPassword` state variable:

```js title="components/Register.js"
// ...
const [error, setError] = React.useState("");
// ...
```

And in our `handleValidation` method, let's replace the `failedValidation` boolean with a string named `errorMessage`.

```js title="components/Register.js"
// ...
const handleValidation = () => {
  let errorMessage = "";

  // Check username
  if (users.find((user) => user.username === username)) {
    errorMessage = "This username already exists";
  }

  // Check the password
  if (password !== confirmPassword) {
    errorMessage = "Passwords do not match";
  }

  // Check if fields are blank
  if (username === "" || email === "" || password === "") {
    errorMessage = "Please fill in all fields";
  }

  setError(errorMessage);
  return errorMessage !== "";
};
// ...
```

Instead of a boolean we are using a string with contains a readable message for the user, and at the bottom of the method, we set the message in the state. If the message is **not** an empty string, validation has failed. This also allows us to display this message in the code, so the user knows what they have done wrong.

Finally, within the `return`, right above the final closing `</>`, let's add in a `<p>` tag:

```js title="components/Register.js"
  // ...
  <input type="submit" value="Submit" />
  </form>
  <p>{error}</p>
</>
//...
```

And there you have it! We have created a Register form where a user can sign up for our application, with user-friendly form validation!

<img src="https://media.giphy.com/media/ER8XNaWhIXhMw7W8s2/giphy.gif" alt="It's Amazing" height="250">
