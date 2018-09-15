package mkgosisejo.controllers.console;

import mkgosisejo.models.CreateHeroModel;
import mkgosisejo.views.console.CreateHeroView;

public class CreateHeroController {
    CreateHeroView _view;
    CreateHeroModel _model;

    public CreateHeroController(CreateHeroView view, CreateHeroModel model){
        this._view = view;
        this._model = model;
    }
}