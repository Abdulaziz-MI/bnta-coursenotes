import React from "react";
import UserList from "../components/UserList";
import Register from "../components/Register";

const UserContainer = () => {
  const [users, setUsers] = React.useState([]);

  return (
    <>
      <UserList users={users} />
      <Register users={users} setUsers={setUsers} />
    </>
  );
};

export default UserContainer;
