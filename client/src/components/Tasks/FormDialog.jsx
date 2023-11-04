import { Card, Dialog } from '@mui/material'
import React from 'react'


const FormDialog = ({open, handleClose}) => {
  return (
    <>
                <Dialog open={open} onClose={handleClose}>
                    <Card sx={{width: 200, height: 200}}>
                        
                    </Card>
                </Dialog>
    </>
  )
}


export default FormDialog
