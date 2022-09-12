export interface Code {
  createAt: string;
  createBy: string;
  version: number;
  uuid: string;
  title: string;
  description: string;
  code: string;
  rating: number;
  languageName: string;
  tagsName: string[];
  _links: any;
}
