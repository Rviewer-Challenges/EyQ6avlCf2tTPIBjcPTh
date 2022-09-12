import * as React from "react";
import { useTheme } from "@mui/material/styles";
import { SelectChangeEvent } from "@mui/material/Select";

import { fetchLanguage } from "../../services/language.service";
import { fetchTags } from "../../services/tag.service";
import {
  fetchSnippetCode,
  fetchSnippetCodeFilter,
} from "../../services/snippetcode.service";

import { Code } from "../../models/code";

export default function CardCodeGridViewModel() {
  const [title, setTitle] = React.useState("");
  const [lang, setLang] = React.useState("");
  const [tags, setTags] = React.useState<string[]>([]);

  const [languageOpt, setLanguageOpt] = React.useState<any[]>([]);
  const [tagsOpt, setTagsOpt] = React.useState<any[]>([]);
  const [codes, setCodes] = React.useState<Code[]>([]);

  const theme = useTheme();

  const initialLoad = async () => {
    const { data: language } = await fetchLanguage();
    const { data: tags } = await fetchTags();
    let { data: codes } = await fetchSnippetCode();

    setLanguageOpt(language._embedded.languages as any[]);
    setTagsOpt(tags._embedded.tags as any[]);
    setCodes(codes._embedded.snippetCodes as Code[]);
  };

  const handleTitleChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setTitle(event.target.value);
  };

  const handleLangChange = (event: SelectChangeEvent) => {
    setLang(event.target.value as string);
  };

  const handleTagsChange = (event: SelectChangeEvent<typeof tags>) => {
    const {
      target: { value },
    } = event;
    setTags(typeof value === "string" ? value.split(",") : value);
  };

  const handleCodeFilter = async () => {
    const { data: codes } = await fetchSnippetCodeFilter(title, lang, tags);
    setCodes(codes._embedded.snippetCodes as Code[]);
  };

  return {
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
  };
}
