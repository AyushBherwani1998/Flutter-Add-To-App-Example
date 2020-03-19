import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MaterialApp(
      home: MyApp(),
    ));

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  static const platform = const MethodChannel('quotesHub.com/Quotes');
  List favoriteQuotes;

  @override
  void initState() {
    platform.setMethodCallHandler(_receiveQuotes);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Color(0xff3d5afe),
        title: Text("Favorite Quotes"),
      ),
      body: favoriteQuotes != null
          ? ListView.builder(
              itemCount: favoriteQuotes.length,
              itemBuilder: (context, index) {
                return Card(
                    child: ListTile(
                  title: Text(favoriteQuotes[index]),
                  trailing: IconButton(
                    icon: Icon(
                      Icons.delete,
                      color: Colors.black,
                    ),
                    onPressed: () => removeQuoteFromList(favoriteQuotes[index]),
                  ),
                ));
              })
          : Container(
              alignment: Alignment.center,
              child: CircularProgressIndicator(),
            ),
    );
  }

  Future<void> _receiveQuotes(MethodCall call) async {
    try {
      if (call.method == "getQuotes") {
        setState(() => favoriteQuotes = List<String>.from(call.arguments));
      }
    } on PlatformException catch (e) {
          
    }
  }

  Future<void> removeQuoteFromList(String quote)async{
    try{
      if(await platform.invokeMethod('removeQuote',{"quote":quote})){
       setState(() => favoriteQuotes.remove(quote));
      }
    }on PlatformException catch(e){
      print(e);
    }
  }
}
