import React from "react";

import html2canvas from "html2canvas";

import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
);

export const options = {
    responsive: true,
    plugins: {
        legend: {
            position: 'top'
        },
        title: {
            display: true,
            text: 'Chart.js Line Chart',
        },
    },
};


const ModalDialog = ({handleClose, content}) => {


    //different story elements
    const id =content.id;
    const title = JSON.stringify(content.title);
    const story = JSON.stringify(content.story);
    const viewCount = JSON.stringify(content.viewCount);
    const downloadCount = JSON.stringify(content.downloadCount);
    const createdAt = JSON.stringify(content.createdAt);
    const viewCountTime = content.viewCountTime;
    const downloadCountTime = content.downloadCountTime;
    const labels=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];
    // for(var i = 0; i < viewCountTime.length; ++i) {
    //     res.push({"Key": i + 1 });
    // }
    const data = {
        labels,
        datasets: [
            {
                label: 'Views Count per Minutes',
                data: viewCountTime,
                borderColor: 'rgb(255, 99, 132)',
                backgroundColor: 'rgba(255, 99, 132, 0.5)',
            },
        ],
    };

    const data2 = {
        labels,
        datasets: [
            {
                label: 'Downloads Count per Minutes',
                data: downloadCountTime,
                borderColor: 'rgb(53, 162, 235)',
                backgroundColor: 'rgba(53, 162, 235, 0.5)',
            },
        ],
    };


    //code for converting and downloading image

    const downloadImage = async () => {
        const element = document.getElementById('story'),
            canvas = await html2canvas(element),
            data = canvas.toDataURL('image/png'),
            link = document.createElement('a');

        link.href = data;
        link.download = 'downloaded-article.png';

        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        fetch('http://localhost:8282/restapi/stories/download/'+id)
            .then(()=> console.log('Image downloaded successfully'))
    }

    const deleteArticle = ()=>{
        fetch('http://localhost:8282/restapi/stories/'+id, { method: 'DELETE' })
            .then(() => {console.log('Article deleted successfully.');
                handleClose()});
    }

    //Retrieving all the dynamic data from our html fetched page.
    return (
        <div className="popup-box">
            <div className="box">
                <span className="close-icon" onClick={handleClose}>x</span>
                {/*Here is our content loaded from the html fetched page*/}
                <div>
                <div id="story">
                        <h1 id="title">
                            {title}
                        </h1>

                         <p id="story">{story}</p>
                        <h6 id="createdAt">created at : {createdAt}</h6>
                    <h6 id="count">view count : {viewCount}, download count : {downloadCount}</h6>
                    <Line options={options} data={data} />
                    <Line options={options} data={data2} />
                </div>
                        {/* <p>{{story}}</p>*/}
                    <button className="button-49" onClick={downloadImage} >Download Article</button>
                    <button className="button-49" onClick={deleteArticle}>Delete Article</button>
                </div>

            </div>
        </div>
    );
};

export default ModalDialog;