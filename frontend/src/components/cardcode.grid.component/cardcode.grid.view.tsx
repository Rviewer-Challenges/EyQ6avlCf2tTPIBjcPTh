import * as React from 'react';
import Box from '@mui/material/Box';

import Grid from '@mui/material/Grid';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Divider from '@mui/material/Divider';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Chip from '@mui/material/Chip';
import Select from '@mui/material/Select';

import CardCode from "../cardcode.component";
import { CodeGrid } from '../../models/codegird';
import CardCodeGridViewModel from './cardcode.grid.viewmodel';
import { MenuProps, getStyles } from './cardcode.grid.style';

export default function CardCodeGrid() {

    const {
        codes,
        languageOpt,
        tagsOpt,
        title,
        lang,
        tags,
        theme,
        initialLoad,
        handleTitleChange,
        handleLangChange,
        handleTagsChange,
        handleCodeFilter,
    } = CardCodeGridViewModel();

    React.useEffect(() => {
        initialLoad();
    }, [])


    return (
        <Box>
            <Stack direction="row"
                justifyContent="flex-start"
                alignItems="center"
                spacing={3}>
                <TextField onChange={handleTitleChange} value={title} id="standard-basic" label="Titulo" variant="standard" fullWidth />
                <FormControl fullWidth>
                    <InputLabel id="demo-simple-select-label">Lenguaje</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={lang}
                        label="Age"
                        onChange={handleLangChange}
                        variant="standard"
                    >
                        {languageOpt.map((language) => {
                            return <MenuItem value={language.name}>{language.name}</MenuItem>
                        })}
                    </Select>
                </FormControl>
                <FormControl fullWidth>
                    <InputLabel id="demo-multiple-chip-label">Tags</InputLabel>
                    <Select
                        labelId="demo-multiple-chip-label"
                        id="demo-multiple-chip"
                        multiple
                        value={tags}
                        onChange={handleTagsChange}
                        variant="standard"
                        renderValue={(selected) => (
                            <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 0.5 }}>
                                {selected.map((value) => (
                                    <Chip key={value} label={value} />
                                ))}
                            </Box>
                        )}
                        MenuProps={MenuProps}
                    >
                        {tagsOpt.map((tag) => (
                            <MenuItem
                                key={tag.name}
                                value={tag.name}
                                style={getStyles(tag.name, tagsOpt, theme)}
                            >
                                {tag.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>

                <Button variant="contained" onClick={() => handleCodeFilter()}>Filtro</Button>
            </Stack>
            <Divider sx={{ py: 1 }} />
            <Grid container spacing={2} sx={{ mt: 2 }}>
                {codes.map((code, index) => (
                    <Grid key={index} item xs={12} sm={6} md={4} lg={3} xl={2} >
                        <CardCode {...code} />
                    </Grid>
                ))}
            </Grid>
        </Box>
    );

}