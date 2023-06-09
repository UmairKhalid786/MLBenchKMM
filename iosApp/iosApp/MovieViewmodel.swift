//
//  MovieViewmodel.swift
//  iosApp
//
//  Created by Umair Ali Khalid on 09/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared


class MoviesViewModel : ObservableObject {
    
    @Published var appState = Resource<ContentResponse>(status: ResourceStatus.loading, data: nil, message: nil, error: nil)
    
    init() {
        GetTrendingMoviesUseCase().invoke { result, error in
            if(result != nil) {
                self.appState = result!
            }
        }
    }
}

extension ContentResponse.Content: Identifiable {
    public typealias ID = Int
    public var id: Int {
        return hash
    }
}
