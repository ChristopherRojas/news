/*
 * Copyright (c) 2020. Christopher Rojas-Garri, christopher.rojas@alumnos.ucn.cl
 * Copyright (c) 2021. Camilo Barrera-Arancibia,camilo.barrera@alumnos.ucn.cl
 * Copyright (c) 2021. Marcelo Lam-Biagguini,marcelo.lam@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.crojas.news.model;

import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mikepenz.fastadapter.items.ModelAbstractItem;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

import cl.ucn.disc.dsm.crojas.news.R;

/**
 * The NewsItem to show in the list.
 * @author Christopher Rojas-Garri
 */
@RequiresApi(api = Build.VERSION_CODES.O) // fixme: ofPattern api
public final class NewsItem extends ModelAbstractItem<News, NewsItem, NewsItem.ViewHolder> {

    /**
     * The ZonedDateTime formatter.
     */
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm d.LLL.yyyy");

    /**
     * The constructor.
     * @param news The news to show.
     */
    public NewsItem(@NonNull News news) {
        super(news);
    }

    /**
     * This function allows to return a new ViewHolder.
     * @param view used to build the ViewHolder.
     * @return the ViewHolder to the views.
     */
    public ViewHolder getViewHolder(@NonNull View view) {
        return new ViewHolder(view);
    }

    /**
     * @return the RecyclerView element.
     */
    @Override
    public int getType() {
        return R.id.am_rv_news;
    }

    /**
     * @return the layout to use.
     */
    @Override
    public int getLayoutRes() {
        return R.layout.item_news;
    }

    /**
     * Bind the holder with the model.
     * @param holder   to used.
     * @param payloads ?.
     */
    @Override
    public void bindView(@NonNull ViewHolder holder, @NonNull List<Object> payloads) {
        super.bindView(holder, payloads);

        //Image uri implement
        // Uri uri = Uri.parse(getModel().getUrlImage()); TODO
        // Setting the holder
        holder.title.setText(getModel().getTitle());
        holder.author.setText(getModel().getAuthor());
        holder.source.setText(getModel().getSource());
        holder.description.setText(getModel().getDescription());
        holder.publishedAt.setText(FORMATTER.format(getModel().getPublishedAt()));
        holder.image.setImageURI(getModel().getUrlImage());
    }

    /**
     * This method clear the holder
     * @param holder to clean.
     */
    @Override
    public void unbindView(@NonNull ViewHolder holder) {
        super.unbindView(holder);

        // Setting the holder
        holder.title.setText(null);
        holder.author.setText(null);
        holder.source.setText(null);
        holder.description.setText(null);
        holder.publishedAt.setText(null);
    }

    /**
     * The ViewHolder Pattern.
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView title;
        protected TextView author;
        protected TextView source;
        protected TextView description;
        protected TextView publishedAt;
        protected SimpleDraweeView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.in_tv_title);
            this.author = itemView.findViewById(R.id.in_tv_autor);
            this.source = itemView.findViewById(R.id.in_tv_source);
            this.description = itemView.findViewById(R.id.in_tv_description);
            this.publishedAt = itemView.findViewById(R.id.in_tv_published_at);
            this.image = itemView.findViewById(R.id.my_image_view);

        }
    }
}