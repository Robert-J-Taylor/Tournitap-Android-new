package roberttaylor.tournitap_android.UI;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import roberttaylor.tournitap_android.Data.DatabaseHandler;
import roberttaylor.tournitap_android.Model.Tournament;
import roberttaylor.tournitap_android.R;
import roberttaylor.tournitap_android.TournamentDetails;

import org.w3c.dom.Text;

import java.util.List;


/**
 * Created by Robert Taylor on 10/22/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Tournament> tournamentItems;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, List<Tournament> tournamentItems) {
        this.context = context;
        this.tournamentItems = tournamentItems;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {

        Tournament tournament = tournamentItems.get(position);

        holder.tournamentName.setText(tournament.getName());
        holder.gameType.setText(tournament.getGameType());
        holder.formatName.setText(tournament.getFormatName());
        holder.numParticipants.setText(tournament.getNumParticipants());

    }

    @Override
    public int getItemCount() {
        return tournamentItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tournamentName;
        public TextView gameType;
        public TextView formatName;
        public TextView numParticipants;
        public Button editButton;
        public Button deleteButton;
        public int id;


        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;

            tournamentName = (TextView) view.findViewById(R.id.tournamentName);
            gameType = (TextView) view.findViewById(R.id.gameType);
            formatName = (TextView) view.findViewById(R.id.formatName);
            numParticipants = (TextView) view.findViewById(R.id.numParticipants);

            editButton = (Button) view.findViewById(R.id.editButton);
            deleteButton = (Button) view.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to next screen/ DetailsActivity
                    int position = getAdapterPosition();

                    Tournament tournament = tournamentItems.get(position);
                    Intent intent = new Intent(context, TournamentDetails.class);
                    intent.putExtra("id",tournament.getId());
                    intent.putExtra("tournamentName", tournament.getName());
                    intent.putExtra("gameType", tournament.getGameType());
                    intent.putExtra("formatName", tournament.getFormatName());
                    intent.putExtra("numParticipants", tournament.getNumParticipants());
                    intent.putExtra("stageType", tournament.getStageType());
                    intent.putExtra("skillLevel", tournament.getSkillLevel());
                    intent.putExtra("totalRounds", tournament.getTotalRounds());
                    intent.putExtra("description", tournament.getDescription());
                    context.startActivity(intent);


                }
            });
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.editButton:
                    int position = getAdapterPosition();
                    Tournament tournament = tournamentItems.get(position);


                    editItem(tournament);

                    break;
                case R.id.deleteButton:
                    position = getAdapterPosition();
                    tournament = tournamentItems.get(position);
                    deleteItem(tournament.getId());

                    break;


            }
        }

        public void deleteItem(final int id) {

            //create an AlertDialog
            alertDialogBuilder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.confirmation_dialog, null);

            Button noButton = (Button) view.findViewById(R.id.noButton);
            Button yesButton = (Button) view.findViewById(R.id.yesButton);

            alertDialogBuilder.setView(view);
            dialog = alertDialogBuilder.create();
            dialog.show();


            noButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //delete the item.
                    DatabaseHandler db = new DatabaseHandler(context);
                    //delete item
                    db.deleteTournament(id);
                    tournamentItems.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                    dialog.dismiss();


                }
            });

        }


        public void editItem(final Tournament tournament) {

            alertDialogBuilder = new AlertDialog.Builder(context);

            inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.popup, null);

            final EditText tournamentName = (EditText) view.findViewById(R.id.tournamentName);
            final EditText gameType = (EditText) view.findViewById(R.id.gameType);
            final EditText formatName = (EditText) view.findViewById(R.id.formatName);
            final EditText numParticipants = (EditText) view.findViewById(R.id.numParticipants);
            final EditText stageType=(EditText) view.findViewById(R.id.stageType);
            final EditText skillLevel=(EditText) view.findViewById(R.id.skillLevel);
            final EditText totalRounds=(EditText) view.findViewById(R.id.totalRounds);
            final EditText description=(EditText) view.findViewById(R.id.description);
            final TextView title = (TextView) view.findViewById(R.id.tile);

            title.setText("Edit Grocery");
            Button saveButton = (Button) view.findViewById(R.id.saveButton);


            alertDialogBuilder.setView(view);
            dialog = alertDialogBuilder.create();
            dialog.show();

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseHandler db = new DatabaseHandler(context);

                    //Update item
                    tournament.setName(tournamentName.getText().toString());
                    tournament.setGameType("GameType:" + gameType.getText().toString());
                    tournament.setFormatName("Format:" + formatName.getText().toString());
                    tournament.setNumParticipants("# of Participants:" + numParticipants.getText().toString());
                    tournament.setStageType("StageType:" + stageType.getText().toString());
                    tournament.setSkillLevel("SkillLevel:" + skillLevel.getText().toString());
                    tournament.setTotalRounds("Total Rounds:" + totalRounds.getText().toString());
                    tournament.setDescription(description.getText().toString());

//                    if (!tournamentName.getText().toString().isEmpty()
//                            && !gameType.getText().toString().isEmpty()
//                            && !formatName.getText().toString().isEmpty()
//                            && !stageType.getText().toString().isEmpty()
//                            && !skillLevel.getText().toString().isEmpty()
//                            && !totalRounds.getText().toString().isEmpty()
//                            && !description.getText().toString().isEmpty())
//                    {
                        db.updateGrocery(tournament);
                        notifyItemChanged(getAdapterPosition(),tournament);
//                    }else {
//                        Snackbar.make(view, "Please Fill Out All Fields", Snackbar.LENGTH_LONG).show();
//                    }

                    dialog.dismiss();

                }
            });

        }
    }

}

