import { Children1 } from "./componets/children-pass";
import { State1 } from "./componets/state-comp";

export default function Intermediate() {
    return (<>
        <h1>Fundametals</h1>
        <hr/>

        <h2>State</h2>
        <State1/>

        <h2>Chlidren</h2>
        <Children1 text="testing">
            <p>Some code in html</p>
        </Children1>
    </>);
}