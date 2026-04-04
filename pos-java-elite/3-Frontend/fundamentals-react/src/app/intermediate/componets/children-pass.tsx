import { FC, ReactNode } from "react";

export const Children1:FC<{text: string, children: ReactNode}> = (props) => {
    return (<>
        <h3>{props.text}</h3>
        {props.children}
    </>);
};