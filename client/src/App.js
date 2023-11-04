import React from 'react';
import Home from './pages/Home.jsx';

function App() {
    const items = [
        { id: 1, title: 'Todo', count: 5 },
        { id: 2, title: 'In Progress', count: 10 },
        // Add more items as needed
    ];

    const clearFilters = () => {
        // Add logic to clear filters here
        console.log('Filters cleared');
    };

    return (
        <div className="App">
            <Home />
            {/* <h1>Hello</h1> */}
            {/* <DropdownCha items={items} onClearFilters={clearFilters} /> */}
        </div>
    );
}

export default App;
