import React from "react";
import { useLocation } from "react-router-dom";
import { useTheme } from "@mui/material/styles";
import { useNavigate } from "react-router-dom";

import HomeIcon from "@mui/icons-material/Home";
import CodeIcon from "@mui/icons-material/Code";
import BookmarkIcon from "@mui/icons-material/Bookmark";

interface NavigationItem {
  title: string;
  path: string;
  icon: any;
}

const navigationMenu: NavigationItem[] = [
  { title: "Home", path: "/app", icon: HomeIcon },
  { title: "Bookmark", path: "/app/bookmark", icon: BookmarkIcon },
  { title: "About", path: "/app/about", icon: CodeIcon },
];

export default function DrawerViweModel() {
  const theme = useTheme();
  const navigate = useNavigate();
  const location = useLocation();
  const [open, setOpen] = React.useState(false);
  const [openMenu, setOpenMenu] = React.useState(false);
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const [navigateMenu] = React.useState<NavigationItem[]>(navigationMenu);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  const goToThisView = (component: string) => {
    navigate(component, { replace: true });
  };

  const handleMenuOpen = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
    setOpenMenu(true);
  };
  const handleMenuClose = () => {
    setAnchorEl(null);
    setOpenMenu(false);
  };

  return {
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
  };
}
