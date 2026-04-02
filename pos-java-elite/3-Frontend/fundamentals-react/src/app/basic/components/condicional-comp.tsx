import { FC } from "react";

export const Condicional: FC<{ name: string, cond: boolean }> = (props) => {
    return (<>
        <h3>Condicional</h3>

        <p>
            <span>Inline condicional:</span>
            <span>{ props.cond ? <span>It is true</span>: <span>It is false</span>}</span>
        </p>
    </>);
};