import { Outlet } from 'react-router-dom';

import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Tooltip from '@mui/material/Tooltip';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import LogoutIcon from '@mui/icons-material/Logout'
import SettingsIcon from '@mui/icons-material/Settings'
import CodeTwoToneIcon from '@mui/icons-material/CodeTwoTone'
import ThunderstormTwoToneIcon from '@mui/icons-material/ThunderstormTwoTone';
import { SvgIcon } from '@mui/material';
import DrawerViweModel from './drawer.viewmodel'

import { AppBar, Drawer, DrawerHeader } from './drawer.style';

export default function MiniDrawer() {

    const {
        anchorEl,
        open,
        openMenu,
        theme,
        navigateMenu,
        location,
        handleDrawerOpen,
        handleDrawerClose,
        goToThisView,
        handleMenuOpen,
        handleMenuClose,
    } = DrawerViweModel();

    return (
        <Box sx={{ display: 'flex' }}>
            <AppBar position="fixed" open={open}>
                <Toolbar>
                    <IconButton
                        color="inherit"
                        aria-label="open drawer"
                        onClick={handleDrawerOpen}
                        edge="start"
                        sx={{
                            marginRight: 5,
                            ...(open && { display: 'none' }),
                        }}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Box sx={{ display: 'flex', opacity: open ? 0 : 1 }} component="div">
                        <Typography variant="h6" noWrap >
                            MyCode
                        </Typography>
                        <CodeTwoToneIcon />
                    </Box>
                </Toolbar>
            </AppBar>
            <Drawer variant="permanent" open={open}>
                <DrawerHeader>
                    <Box sx={{ display: 'flex', opacity: open ? 1 : 0 }} component="div">
                        <Typography variant="h6" noWrap >
                            MyCode
                        </Typography>
                        <CodeTwoToneIcon />
                    </Box>
                    <IconButton onClick={handleDrawerClose}>
                        {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
                    </IconButton>
                </DrawerHeader>
                <Divider />
                <List >
                    <ListItem disablePadding sx={{ display: 'block' }}>
                        <ListItemButton
                            id="basic-button"
                            aria-controls={openMenu ? 'basic-menu' : undefined}
                            aria-haspopup="true"
                            aria-expanded={openMenu ? 'true' : undefined}
                            onClick={handleMenuOpen}
                            sx={{
                                minHeight: 48,
                                justifyContent: open ? 'initial' : 'center',
                                px: 2.5,
                            }}
                        >
                            <ListItemAvatar sx={{
                                minWidth: 0,
                                mr: open ? 3 : 'auto',
                                justifyContent: 'center',
                            }}>
                                <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
                            </ListItemAvatar>
                            <ListItemText primary="Username" sx={{ opacity: open ? 1 : 0 }} />
                        </ListItemButton>
                        <Menu
                            id="basic-menu"
                            anchorEl={anchorEl}
                            open={openMenu}
                            onClose={handleMenuClose}
                            MenuListProps={{
                                'aria-labelledby': 'basic-button',
                            }}
                            anchorOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                        >
                            <MenuItem onClick={handleMenuClose}>
                                <ListItemIcon>
                                    <SettingsIcon fontSize="small" />
                                </ListItemIcon>
                                Profile
                            </MenuItem>
                            <Divider />
                            <MenuItem onClick={handleMenuClose}>
                                <ListItemIcon>
                                    <LogoutIcon fontSize="small" />
                                </ListItemIcon>
                                Logout
                            </MenuItem>
                        </Menu>
                    </ListItem>
                    <Divider />
                    {navigateMenu.map(({ title, path, icon }) => (
                        <ListItem key={title} disablePadding
                            sx={{
                                display: 'block',
                                color: location.pathname === path ? theme.palette.primary.main : null
                            }} >
                            <ListItemButton onClick={() => goToThisView(path)}
                                sx={{
                                    minHeight: 48,
                                    justifyContent: open ? 'initial' : 'center',
                                    px: 2.5,
                                }}
                            >
                                <Tooltip title={title} placement='right-end'>
                                    <ListItemIcon
                                        sx={{
                                            minWidth: 0,
                                            mr: open ? 3 : 'auto',
                                            justifyContent: 'center',
                                            color: location.pathname === path ? theme.palette.primary.main : null
                                        }}
                                    >
                                        <SvgIcon component={icon} />
                                    </ListItemIcon>
                                </Tooltip>
                                <ListItemText primary={title} sx={{ opacity: open ? 1 : 0 }} />
                            </ListItemButton>
                        </ListItem>
                    ))}
                </List>
                <List style={{ marginTop: 'auto' }} >
                    <ListItem disablePadding sx={{
                        display: 'block',
                        color: theme.palette.warning.main,
                        backgroundColor: theme.palette.primary.main,
                        borderRadius: 5
                    }}>
                        <ListItemButton
                            sx={{
                                minHeight: 48,
                                justifyContent: open ? 'initial' : 'center',
                                px: 2.5,
                            }}
                        >
                            <Tooltip title='Agregar Codigo' placement='right-end'>
                                <ListItemIcon
                                    sx={{
                                        minWidth: 0,
                                        mr: open ? 3 : 'auto',
                                        justifyContent: 'center',
                                    }}
                                >
                                    <ThunderstormTwoToneIcon color='warning' />
                                </ListItemIcon>
                            </Tooltip>
                            <ListItemText primary={<Typography variant='h6'>Codigo +</Typography>} sx={{ opacity: open ? 1 : 0 }} />
                        </ListItemButton>
                    </ListItem>

                </List>
            </Drawer>
            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                <DrawerHeader />
                <Outlet />
            </Box>
        </Box>
    );
}
