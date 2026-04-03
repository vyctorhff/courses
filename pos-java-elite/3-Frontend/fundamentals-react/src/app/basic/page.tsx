import { ArgumentComp, ArgumentComp2, ArgumentComp3 } from "./components/arguments-comp";
import { Condicional, Condicional2 } from "./components/condicional-comp";
import { Iterator2, IteratorSimple } from "./components/for-comp";
import { HelloComp } from "./components/hello-comp";

export default function Basic() {
  return (<>
    <h1>Fundametals</h1>

    <hr/>
    <h2>Components</h2>
    <HelloComp/>
    <ArgumentComp name="fulano"/>
    <ArgumentComp name="fulano" last="de tal"/>
    <ArgumentComp2 person={{ name: "fulano"}} size={20}/>
    <ArgumentComp3 person={{ name: "fulano"}} size={20}/>
    <hr/>

    <h2>Condicional</h2>
    <Condicional name="bbb" cond={false}/>
    <Condicional2 cond={true}/>
    <hr/>

    <h2>Iterator</h2>
    <IteratorSimple/>
    <Iterator2/>
    <hr/>
  </>);
}
