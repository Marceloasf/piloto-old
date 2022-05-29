import React, { useState, useEffect } from "react";
import {
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  Paper,
  makeStyles,
  Container
} from "@material-ui/core";
import { UserService } from "../../service";
import { CustomFab } from "../../components";
import { User } from "../../types";

const useStyles = makeStyles({
  table: {
    minWidth: 650
  }
});

const UserList = props => {
  const [users, setUsers] = useState<User[]>([]);
  const classes = useStyles();

  useEffect(() => {
    UserService.findAll().then(({ data }) => setUsers(data));
  }, [props]);

  return (
    <>
      <Container>
        <TableContainer component={Paper}>
          <Table className={classes.table} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>User</TableCell>
                <TableCell>Birth date</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {users.map(user => (
                <TableRow key={user.id}>
                  <TableCell component="th" scope="row">
                    {user.name}
                  </TableCell>
                  <TableCell>{user.birthDate}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Container>
      <CustomFab onClick={() => props.history.push("/users/new")} />
    </>
  );
};

export { UserList };
