import { useNavigate } from "react-router-dom";

export default function CardcodeViewModel() {
  const navigate = useNavigate();

  const habdleAddBookmark = (id: string) => {
    console.log("Add Bookmark: ID: " + id);
  };

  const habdleCopyLink2Share = (id: string) => {
    console.log("Copy link 2 share code, ID: " + id);
  };

  const handleNavigate2Code = (id: string) => {
    navigate("/app/code/" + id);
  };

  return {
    habdleAddBookmark,
    habdleCopyLink2Share,
    handleNavigate2Code,
  };
}
