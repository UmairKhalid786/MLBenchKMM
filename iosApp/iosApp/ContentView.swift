import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var vm: MoviesViewModel = MoviesViewModel()
    
    var body: some View {
        VStack {
            if self.vm.appState.status == ResourceStatus.loading {
                ProgressView()
            } else if self.vm.appState.status == ResourceStatus.success {
                List {
                    ForEach(self.vm.appState.data!.results.filter({ con in
                        con.title != nil
                    })) { p in
                        Text(p.title ?? "")
                    }
                }
            } else {
                Text("Something went wrong")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
