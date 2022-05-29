import React from "react";
import { Fab, makeStyles } from "@material-ui/core";
import { green } from "@material-ui/core/colors";
import AddIcon from "@material-ui/icons/Add";

const useStyles = makeStyles(theme => ({
  fab: {
    position: "fixed",
    bottom: theme.spacing(2),
    right: theme.spacing(2)
  },
  fabGreen: {
    color: theme.palette.common.white,
    backgroundColor: green[500],
    "&:hover": {
      backgroundColor: green[600]
    }
  }
}));

type Props = {
  onClick: () => void;
}

export const CustomFab: React.FC<Props> = ({ onClick }) => {
  const classes = useStyles();

  const fab = {
    className: classes.fab,
    icon: <AddIcon />,
    label: "Add"
  };

  return (
    <Fab aria-label={fab.label} className={fab.className} color='primary' onClick={onClick}>
      {fab.icon}
    </Fab>
  );
};
