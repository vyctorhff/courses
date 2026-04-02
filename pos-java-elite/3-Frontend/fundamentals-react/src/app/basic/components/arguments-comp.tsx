import { FC } from "react";

export const ArgumentComp: FC<{ name: string }> = (props) => {
    return (<>
        <h3>Props comps</h3>

        <p>First props name: {props.name}</p>
    </>);
};

export const ArgumentComp2: FC = () => {
    return (<></>);
};
