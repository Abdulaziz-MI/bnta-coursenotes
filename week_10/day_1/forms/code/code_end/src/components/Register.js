import React from "react";

const Register = ({ users, setUsers }) => {
  const [username, setUsername] = React.useState("");
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [confirmPassword, setConfirmPassword] = React.useState("");
  const [error, setError] = React.useState("");

  const handleValidation = () => {
    let errorMessage = "";

    // Check if fields are blank
    if (username === "" || email === "" || password === "") {
      errorMessage = "Please fill in all fields";
    }

    // Check username
    if (users.find((user) => user.username === username)) {
      errorMessage = "This username already exists";
    }

    // Check password
    if (password !== confirmPassword) {
      errorMessage = "Passwords do not match";
    }

    setError(errorMessage);
    return errorMessage !== "";
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!handleValidation()) {
      let newUsers = users;
      newUsers.push({
        username,
        email,
        password,
        confirmPassword,
      });
      setUsers([...newUsers]);
    }
  };

  return (
    <div>
      <h2>Sign up</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Username"
        />
        <input
          type="text"
          name="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="Email"
        />
        <input
          type="password"
          name="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Password"
        />
        <input
          type="password"
          name="confirmPassword"
          value={confirmPassword}
          onChange={(e) => setConfirmPassword(e.target.value)}
          placeholder="Confirm Password"
        />
        <input type="submit" value="Submit" />
      </form>
      <p>{error}</p>
    </div>
  );
};

export default Register;
