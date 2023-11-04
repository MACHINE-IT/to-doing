import { Box, Grid } from '@mui/material';
import Button from '@mui/material/Button';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import { DataGrid } from '@mui/x-data-grid';
import React, { useState } from 'react';


 const TaskTable = () => {

    const theme = createTheme({
        palette: {
            primary: {
                main: '#26b8da',
                contrastText: '#fff'
            }
        }
      });
    const rows = [
        { id: 1, lastName: 'Snow', firstName: 'Jon', age: 35 },
        { id: 2, lastName: 'Lannister', firstName: 'Cersei', age: 42 },
        { id: 3, lastName: 'Lannister', firstName: 'Jaime', age: 45 },
        { id: 4, lastName: 'Stark', firstName: 'Arya', age: 16 },
        { id: 5, lastName: 'Targaryen', firstName: 'Daenerys', age: null },
        { id: 6, lastName: 'Melisandre', firstName: null, age: 150 },
        { id: 7, lastName: 'Clifford', firstName: 'Ferrara', age: 44 },
        { id: 8, lastName: 'Frances', firstName: 'Rossini', age: 36 },
        { id: 9, lastName: 'Roxie', firstName: 'Harvey', age: 65 },
    ];

    const columns = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'lastName', headerName: 'Last name', width: 130 },
        {
            field: 'age',
            headerName: 'Age',
            type: 'number',
            width: 90,
        },
        {
            field: 'fullName',
            headerName: 'Full name',
            description: 'This column has a value getter and is not sortable.',
            sortable: false,
            width: 160,
            valueGetter: (params) =>
                `${params.row.firstName || ''} ${params.row.lastName || ''}`,
        },
    ];
    const [Rows, setRows] = useState(rows);
    const [Columns, setColumns] = useState(columns);


    const removeColumns = (columnName) => {
        const updatedColumns = columns.filter(column => column.field !== columnName);
        console.log(updatedColumns);
        setColumns(updatedColumns);
    }

      
      

    return (
        <>
        {/* <DropDownCha items={filteredItems} onClearFilters={handleClearFilters} /> */}
        <div style={{width: '100%'}}>
            <Grid container>
            <Grid sx={{display: 'flex', flexDirection: 'row'}}>
                <ThemeProvider theme={theme}>
                    <Box sx={{
                        display: 'flex',
                        // flexDirection: 'row-reverse'
                    }}>
                        <Button variant='contained' color='primary'>Add Task</Button>
                    </Box>
                </ThemeProvider>
            </Grid>
            <Grid item>
            {/* <div style={{height: 400, width: 'auto' }}> */}
                <DataGrid
                sx={{width: 1/2}}
                    columns={Columns}
                    rows={Rows}
                    initialState={{
                        pagination: {
                            paginationModel: {
                                page: 0, pageSize: 5
                            }
                    }}}
                    pageSizeOptions={[5, 10]}
                    checkboxSelection
                />

                <div>
                    <button style={{color: "black"}} onClick={() => removeColumns("age")}><p>Remove Row </p></button>
                </div>

            {/* </div> */}
            </Grid>
            </Grid>
        </div>
        </>
    )
}


export default TaskTable