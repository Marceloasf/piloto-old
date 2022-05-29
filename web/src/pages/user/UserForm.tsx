import React, { useState } from "react";
import { MatchParams, User } from "../../types";
import { Container, TextField } from "@material-ui/core";
import { Row, Col } from "react-bootstrap";

type Props = {
  match: MatchParams;
};

const UserForm: React.FC<Props> = ({ match }) => {
  const [user, setUser] = useState<User>({
    id: "",
    name: "",
    birthDate: ""
  });

  const handleChange = (name, value) => {
    setUser(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  return (
    <Container fixed>
      <Row>
        <Col md={6}>
          <TextField
            name="name"
            id="standard-basic"
            label="Name"
            value={user.name}
            onChange={({ target }) => handleChange("name", target.value)}
          />
        </Col>
        <Col md={6}>
          <TextField
            name="birthDate"
            id="standard-basic"
            label="Birthdate"
            type="number"
            value={user.birthDate}
            onChange={({ target }) => handleChange("birthDate", target.value)}
          />
        </Col>
      </Row>
    </Container>
  );
};

export { UserForm };
