import { Box, Button, Typography } from "@mui/material";
import React, { useContext } from "react";
import { useNavigate } from "react-router";
import TaskContext from "../../context/Context";
import { getAllTasks } from "../../services/UserTasks";


export function Headline({HeadlineName, HeadlineIcon}) {
    const navigate = useNavigate();
    const {myState, setMyState} = useContext(TaskContext);

    const handleClick = async () => {
        setMyState("react context works");
        console.log("clicked sort by");
        const response = await getAllTasks();
        console.log(response)
    }

  return <Box sx={{display: "flex", padding: 1, flexDirection: 'row', color: 'white'
	// paddingTop: "20px",
	// width: "100%",
	// position: "fixed",
	// top: "40px",
	// background: "red",
	// zIndex: 90,
	// backdropFilter: "transparent"
}}>
            <Box sx={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
               {HeadlineIcon}
            </Box>
            <Box sx={{paddingLeft: 2, flex: 2}}>
                <Typography variant="h4">{HeadlineName}</Typography>
            </Box>
            <Box>
                <Button onClick={handleClick}  variant="contained" color="secondary">
                    Sort By
                </Button>
                <Typography>
                    {myState}
                </Typography>
            </Box>
        </Box>;
}
  