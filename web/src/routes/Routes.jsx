import React from "react";
import { Switch, Route } from "react-router-dom";
import { HomePage, UserList, UserForm } from "../pages";

export const Routes = () => {
  return (
    <Switch>
      <Route path="/" component={HomePage} exact />

      <Route path="/users" component={UserList} exact />
      <Route path="/users/:id" component={UserForm} />
    </Switch>
  );
};
