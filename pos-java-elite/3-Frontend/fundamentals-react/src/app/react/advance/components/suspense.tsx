import { FC, use } from "react";

export const Suspense1:FC<{callBack:Promise<string>}> = ({callBack}) => {
    // use wait promise or context to be resolve
    const value = use(callBack);
    return (<><div>O valor é: {value}</div></>);
};
