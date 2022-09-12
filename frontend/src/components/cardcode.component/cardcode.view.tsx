import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Avatar from '@mui/material/Avatar';
import Chip from '@mui/material/Chip';
import Stack from '@mui/material/Stack';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import ShareIcon from '@mui/icons-material/Share';
import BookmarkIcon from '@mui/icons-material/Bookmark';
import VisibilityIcon from '@mui/icons-material/Visibility';
import Rating from '@mui/material/Rating';

import CardcodeViewModel from './cardcode.viewmodel';
import { Code } from '../../models/code';

export default function CardCode({ uuid, title, createAt, description, languageName, tagsName, createBy, rating, _links }: Code) {

    const {
        habdleAddBookmark,
        habdleCopyLink2Share,
        handleNavigate2Code
    } = CardcodeViewModel();

    return (
        <Card sx={{ maxWidth: 350 }}>
            <CardHeader
                avatar={
                    <Avatar aria-label="recipe" src={`/assets/img/${languageName}.png`} />
                }
                title={title}
                subheader={`Publicado: ${createAt}`}
            />
            <CardContent>
                <Typography variant="body2" color="text.secondary">{description}</Typography>
                <Stack direction="row" spacing={1}>
                    {tagsName.map((text, index) => {
                        return <Chip key={index} label={text} />
                    })}
                </Stack>
                <Rating name="read-only" value={rating} readOnly precision={0.5} size="small" />
            </CardContent>
            <CardActions disableSpacing>
                <IconButton aria-label="add to favorites" onClick={() => { habdleAddBookmark(uuid) }}>
                    <BookmarkIcon />
                </IconButton>
                <IconButton aria-label="share" onClick={() => { habdleCopyLink2Share(uuid) }}>
                    <ShareIcon />
                </IconButton>
                <IconButton aria-label="show" onClick={() => { handleNavigate2Code(uuid) }}>
                    <VisibilityIcon />
                </IconButton>
            </CardActions>
        </Card>
    );
}
