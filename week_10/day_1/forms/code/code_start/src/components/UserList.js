import User from "./User";

const UserList = ({ users }) => {
  const userComponents = users.map((user, index) => {
    return <User user={user} key={index} />;
  });

  return <ul>{userComponents}</ul>;
};

export default UserList;
