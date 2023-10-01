const User = ({ user }) => {
  return (
    <li>
      {user.username} - {user.email}
    </li>
  );
};

export default User;
