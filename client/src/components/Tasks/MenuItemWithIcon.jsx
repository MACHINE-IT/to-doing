import { MenuItem } from '@mui/material';
import React from 'react';

const MenuItemWithIcon = ({options}) => {
    
    const arrayOfObjects = options;

    const some =[{
        'icon': 'iconname with case sensitive',
        'option_title': 'Daily'
    }]

  return (
    <> {
        some.map(object => (
            <MenuItem>
                
            </MenuItem>
        ))
    }
    </>
  )
}

export default MenuItemWithIcon
