import {
    Box,
    Button,
    Checkbox,
    List,
    ListItem,
    ListItemText,
    TextField,
    Typography,
} from '@material-ui/core';
import ClearIcon from '@material-ui/icons/Clear';
import React, { useEffect, useRef, useState } from 'react';
import './d.css';

const DropdownCha = ({ items, onClearFilters }) => {
    const [searchText, setSearchText] = useState('');
    const [selectedItems, setSelectedItems] = useState([]);
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);
    const dropdownRef = useRef(null);

    const handleSelectItem = (item) => {
        setSelectedItems((prevSelectedItems) =>
            prevSelectedItems.includes(item)
                ? prevSelectedItems.filter((i) => i !== item)
                : [...prevSelectedItems, item]
        );
    };

    const handleClearFilters = () => {
        setSelectedItems([]);
        onClearFilters();
    };

    const toggleDropdown = () => {
        setIsDropdownOpen(!isDropdownOpen);

        // Add this code to close the dropdown when the toggle button is clicked again.
        if (isDropdownOpen) {
          setIsDropdownOpen(false);
        }
    };

    useEffect(() => {
        // Add event listener to close dropdown when clicking outside
        function handleClickOutside(event) {
            if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setIsDropdownOpen(false);
            }
        }

        console.log("After toggle: isDropdownOpen", isDropdownOpen);

        // Bind the event listener
        document.addEventListener('mousedown', handleClickOutside);

        // Unbind the event listener on component unmount
        return () => {
            document.removeEventListener('mousedown', handleClickOutside);
        };


    }, []);

    return (
        <div>
            <Box display="flex" alignItems="center">
                <Button disableRipple={true} disableFocusRipple={true} disableTouchRipple={true} onClick={toggleDropdown}>
                    Toggle Dropdown
                </Button>
                {selectedItems.length > 0 && (
                    selectedItems.map((selectedItem) => (
                        <Typography key={selectedItem} variant="body1">
                            {items.find(item => item.id === selectedItem).title}
                        </Typography>
                    ))
                )}
            </Box>

            {isDropdownOpen && (
                <div className="dropdown-card dropdown-card-shadow" ref={dropdownRef}>
                    <TextField
                        label="Search"
                        variant="outlined"
                        value={searchText}
                        onChange={(e) => setSearchText(e.target.value)}
                    />

                    <List>
                        {items
                            .filter((item) =>
                                item.title.toLowerCase().includes(searchText.toLowerCase())
                            )
                            .map((item) => (
                                <ListItem className="custom-list-item" key={item.id}>

                                    <Checkbox
                                        checked={selectedItems.includes(item.id)}
                                        onChange={() => handleSelectItem(item.id)}
                                    />

                                    <ListItemText
                                        primary={item.title}
                                        secondary={
                                            <Typography variant="body2">
                                                 {item.count}
                                            </Typography>
                                        }
                                    />
                                </ListItem>
                            ))}
                    </List>

                    <Box display="flex" justifyContent="flex-end">
                        <Button
                            variant="outlined"
                            color="secondary"
                            startIcon={<ClearIcon />}
                            onClick={handleClearFilters}
                        >
                            Clear Filters
                        </Button>
                    </Box>
                </div>
            )}
        </div>
    );
};

export default DropdownCha;
