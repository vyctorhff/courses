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

export const Condicional2: FC<{ cond: boolean }> = (props) => {
    if (props.cond) {
        return (<>
            <p>With if: true</p>
        </>)
    }

    return (<>
        <p>With if: false</p>
    </>);
};

export const Condicional3: FC<{ cond: boolean }> = ({ cond }) => {
    return (<>
        <h4>Condicional 3</h4>
        { cond && <p>With if: true</p>}
        { !cond && <p>With if: false</p>}
    </>)
};
