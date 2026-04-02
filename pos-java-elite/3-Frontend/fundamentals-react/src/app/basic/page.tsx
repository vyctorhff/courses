import { ArgumentComp } from "./components/arguments-comp";
import { Condicional } from "./components/condicional-comp";
import { HelloComp } from "./components/hello-comp";

export default function Basic() {
  return (<>
    <h1>Fundametals</h1>

    <hr/>
    <h2>Components</h2>
    <HelloComp/>
    <ArgumentComp name="fulano"/>
    <hr/>

    <Condicional name="bbb" cond={false}/>
    <hr/>
  </>);
}
