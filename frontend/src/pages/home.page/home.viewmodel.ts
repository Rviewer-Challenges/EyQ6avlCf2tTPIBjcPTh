import * as React from "react";

import { Code } from "../../models/code";
import { fetchSnippetCode } from "../../services/snippetcode.service";

export default function HomeViewModel() {
  const [codes, setCodes] = React.useState<Code[]>([]);
  const [error, setErros] = React.useState<boolean>(false);

  const getSnippetCode = async () => {
    let { data, status } = await fetchSnippetCode();
    if (status === 200) {
      setCodes(data._embedded.snippetCodes as Code[]);
    } else {
      setErros(true);
    }
  };
  return {
    codes,
    error,
    getSnippetCode,
  };
}
